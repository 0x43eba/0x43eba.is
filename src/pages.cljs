(ns pages
  (:require ["./html.mjs" :as h]))

(defn- ordinal [i]
  (.padStart (str (inc i)) 2 "0"))

(def ^:private clock-script "
(function () {
  var el = document.querySelector('.clock');
  if (!el) return;
  var tz = el.dataset.timezone;
  var label = el.dataset.label;

  function tick() {
    try {
      var time = new Intl.DateTimeFormat('en-GB', {
        timeZone: tz, hour: '2-digit', minute: '2-digit', hour12: false,
      }).format(new Date());
      el.textContent = label ? label + ' ' + time : time;
      el.hidden = false;
    } catch (e) {
      el.remove();
      return;
    }
    setTimeout(tick, 30000);
  }

  tick();
})();
")

(defn- masthead [{:keys [avatar name role]}]
  [:header.masthead.rise
   [:span.avatar
    [:img {:src (:src avatar) :alt (:alt avatar) :width 92 :height 92}]]
   [:div.masthead-text
    [:h1.name name]
    (when (seq role)
      [:p.role
       ;; .role-part spans sit in normal text flow, so the space between them matters
       (interpose " "
         (map-indexed
           (fn [i part]
             (if (zero? i)
               [:span.role-part part]
               [:span.role-part [:span.sep "//"] part]))
           role))])]])

(defn- intro [{:keys [lead aside body]}]
  [:section.rise
   (when (seq lead) [:p.lead (h/raw lead)])
   (when (seq aside) [:p.aside (h/raw aside)])
   (when (seq body)
     [:div.prose (map (fn [p] [:p (h/raw p)]) body)])])

(defn- focus-section [{:keys [label items]}]
  [:section.rise
   [:h2.label label]
   [:ul.grid
    (map-indexed
      (fn [i {:keys [title body]}]
        [:li
         [:h3 [:span.num (ordinal i)] title]
         [:p (h/raw body)]])
      items)]])

(def ^:private email-icon
  [:svg {:width 15 :height 15 :viewBox "0 0 24 24" :fill "none" :stroke "currentColor"
         :stroke-width "1.8" :stroke-linecap "round" :stroke-linejoin "round"
         :aria-hidden "true"}
   [:rect {:x 2 :y 4.5 :width 20 :height 15 :rx 2}]
   [:path {:d "m2.5 6 9.5 6.5L21.5 6"}]])

(defn- actions [{:keys [email links]}]
  [:div.actions
   (when email
     [:a.btn {:href (str "mailto:" (:address email))}
      email-icon
      (:label email)])
   (map (fn [{:keys [label href external]}]
          [:a.link (cond-> {:href href}
                     external (assoc :target "_blank" :rel "noopener noreferrer"))
           label
           (when external [:span.arrow {:aria-hidden "true"} "↗"])])
        links)])

(defn- colophon [{:keys [items clock]}]
  [:p.colophon
   (map-indexed
     (fn [i item]
       (if (zero? i)
         [:span item]
         (list [:span.dot {:aria-hidden "true"} "·"] [:span item])))
     items)
   (when clock
     (list [:span.dot {:aria-hidden "true"} "·"]
           [:span.clock {:data-timezone (:time-zone clock)
                         :data-label (:label clock)
                         :hidden true}]))])

(defn- home-main [site]
  [:main.page
   (masthead site)
   [:hr.rule.rise]
   (intro site)
   (when (:focus site)
     (list [:hr.rule.rise]
           (focus-section (:focus site))))
   [:hr.rule.rise]
   [:section.rise
    (actions site)
    (when (:colophon site)
      (colophon (:colophon site)))]])

(defn- layout [{:keys [meta] :as site} body]
  [:html {:lang "en"}
   [:head
    [:meta {:charset "UTF-8"}]
    [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
    [:title (:title meta)]

    [:meta {:name "color-scheme" :content "dark light"}]
    [:meta {:name "theme-color" :content (:theme-color meta)}]
    [:link {:rel "icon" :type "image/svg+xml" :href "/images/favicon.svg"}]
    [:link {:rel "canonical" :href (:url meta)}]

    ;; Fonts
    [:link {:rel "preconnect" :href "https://fonts.googleapis.com"}]
    [:link {:rel "preconnect" :href "https://fonts.gstatic.com" :crossorigin true}]
    [:link {:href "https://fonts.googleapis.com/css2?family=Instrument+Serif:ital@0;1&family=Inter:wght@400;500;600&display=swap"
            :rel "stylesheet"}]

    ;; Styles
    [:link {:rel "stylesheet" :href "/css/styles.css"}]

    ;; SEO
    [:meta {:name "description" :content (:description meta)}]
    [:meta {:name "author" :content (:author meta)}]
    [:meta {:name "keywords" :content (:keywords meta)}]
    [:meta {:name "robots" :content "index, follow"}]

    ;; Open Graph
    [:meta {:property "og:type" :content "website"}]
    [:meta {:property "og:site_name" :content (:title meta)}]
    [:meta {:property "og:title" :content (:title meta)}]
    [:meta {:property "og:description" :content (:description meta)}]
    [:meta {:property "og:url" :content (:url meta)}]
    [:meta {:property "og:image" :content (str (:url meta) (:image meta))}]

    ;; Twitter
    [:meta {:name "twitter:card" :content "summary_large_image"}]
    [:meta {:name "twitter:title" :content (:title meta)}]
    [:meta {:name "twitter:description" :content (:description meta)}]
    [:meta {:name "twitter:image" :content (str (:url meta) (:image meta))}]]
   [:body
    body
    (when (get-in site [:colophon :clock])
      [:script (h/raw clock-script)])]])

(defn home [site]
  (h/page (layout site (home-main site))))
