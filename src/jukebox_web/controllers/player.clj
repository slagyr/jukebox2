(ns jukebox-web.controllers.player
  (:require
    [jukebox-player.core :as player]
    [jukebox-web.models.user :as user]
    [jukebox-web.models.playlist :as playlist]))

(defn play [request]
  (player/play!)
  {:status 302 :headers {"Location" "/playlist"}})

(defn pause [request]
  (player/pause!)
  {:status 302 :headers {"Location" "/playlist"}})

(defn skip [request]
  (when-let [current-user (-> request :session :current-user)]
    (player/skip!)
    (user/increment-skip-count! current-user))
  {:status 302 :headers {"Location" "/playlist"}})
