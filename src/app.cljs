(ns app
  (:require ["express$default" :as express]
            ["node:path" :as path]
            ["node:url" :refer [fileURLToPath]]
            ["./site.mjs" :as site]
            ["./pages.mjs" :as pages]))

(def ^:private dirname (path/dirname (fileURLToPath js/import.meta.url)))

(def app (express))

(.use app
      (.static express (path/join dirname ".." "public")
               #js {:maxAge (if (= "production" js/process.env.NODE_ENV) "7d" 0)}))

(.get app "/"
      (fn [_req res]
        (.send res (pages/home site/config))))

(def ^:private port (or js/process.env.PORT 3000))

(.listen app port
         (fn []
           (println (str "Server listening on port " port))))
