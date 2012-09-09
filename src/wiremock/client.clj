(ns wiremock.client
  (:require [clj-http.client :as client])
  (:require (clj-json [core :as json]) :verbose)
  (:import (com.github.tomakehurst.wiremock WireMockServer)))

(defn start-wiremock-server
  ([port]
    (def admin-base-url (str "http://localhost:" port "/__admin"))
    (let [server (WireMockServer. port)]
      (.start server)
      server))
  ([]
    (start-wiremock-server WireMockServer/DEFAULT_PORT)))

(defn stop [wiremock-server]
  (.stop wiremock-server))

(defn wiremock-single-test-fixture [f]
  (let [server (start-wiremock-server)]
    (f)
    (stop server)))

(defn stub [mapping]
  (let [body (json/generate-string mapping)
        url (str admin-base-url "/mappings/new")]
    (client/post url { :body body })))

(defn reset []
  (let [url (str admin-base-url "/reset")]
    (client/post url)))