(ns wiremock.client.servertests
  (:use [wiremock.client])
  (:use [clojure.test])
  (:require [clj-http.client :as client])
  (:import java.io.IOException))

(defn- get-status-for [url]
  (:status
    (client/get url {:throw-exceptions false})))

(deftest starts-and-stops-server
  (let [server (start-wiremock-server 8083)]
    (is (= 404 (get-status-for "http://localhost:8083")))
    (stop server)
    (is (thrown? IOException (get-status-for "http://localhost:8083")))))


(deftest starts-server-on-default-port
  (let [server (start-wiremock-server)]
    (is (= 404 (get-status-for "http://localhost:8080")))
    (stop server)))

