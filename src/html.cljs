(ns html
  (:require [clojure.string :as str]))

(defn escape [s]
  (-> (str s)
      (str/replace "&" "&amp;")
      (str/replace "<" "&lt;")
      (str/replace ">" "&gt;")
      (str/replace "\"" "&quot;")))

;; Marks a string as pre-rendered HTML the renderer must not escape.
(defn raw [s] {:html/raw (str s)})

(defn raw? [x] (and (map? x) (contains? x :html/raw)))

(def ^:private void-tags
  #{"area" "base" "br" "col" "embed" "hr" "img" "input" "link" "meta" "source" "track" "wbr"})

;; :div.a.b -> ["div" "a b"]
(defn- parse-tag [kw]
  (let [parts (str/split (name kw) #"\.")]
    [(first parts)
     (when (next parts) (str/join " " (rest parts)))]))

(defn- render-attr [k v]
  (cond
    (or (nil? v) (false? v)) ""
    (true? v) (str " " (name k))
    :else (str " " (name k) "=\"" (escape v) "\"")))

(defn- render-attrs [attrs shorthand-class]
  (let [attrs (if shorthand-class
                (assoc attrs :class (if-let [c (:class attrs)]
                                      (str shorthand-class " " c)
                                      shorthand-class))
                attrs)]
    (apply str (map (fn [[k v]] (render-attr k v)) attrs))))

(declare render)

(defn- render-element [[tag & body]]
  (let [[tag-name shorthand-class] (parse-tag tag)
        head (first body)
        has-attrs (and (map? head) (not (raw? head)))
        attrs (if has-attrs head {})
        children (if has-attrs (rest body) body)]
    (if (contains? void-tags tag-name)
      (str "<" tag-name (render-attrs attrs shorthand-class) " />")
      (str "<" tag-name (render-attrs attrs shorthand-class) ">"
           (apply str (map render children))
           "</" tag-name ">"))))

(defn render [x]
  (cond
    (nil? x) ""
    (raw? x) (:html/raw x)
    (vector? x) (render-element x)
    (seq? x) (apply str (map render x))
    :else (escape x)))

(defn page [hiccup]
  (str "<!DOCTYPE html>\n" (render hiccup)))
