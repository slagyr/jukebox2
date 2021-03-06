(ns jukebox-web.views.library
  (:require [jukebox-web.views.layout :as layout]
            [jukebox-web.models.library :as library])
  (:use [hiccup core page-helpers]
        [hiccup core form-helpers]
        [jukebox-player.tags]
        [jukebox-web.util.file :only (relative-uri)]
        [clojure.string :only (join split)]))

(defn display-file [file]
  (if (library/track? file)
    (link-to (str "/playlist/add/" (relative-uri file)) (.getName file))
    (link-to (str "/library/browse/" (relative-uri file)) (.getName file))))

(defn browse [path files]
  (layout/main "browse library"
     [:h3 "files in " path]
     [:ul (map #(vector :li (display-file %)) files)]))
