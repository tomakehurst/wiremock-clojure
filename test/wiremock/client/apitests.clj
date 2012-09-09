(ns wiremock.client.apitests
  (:use [wiremock.client])
  (:use [clojure.test])
  (:require [clj-http.client :as client]))

(use-fixtures :each wiremock-single-test-fixture)

(defn- abs-url [relative-url]
  (str "http://localhost:8080" relative-url))

(defn status-for-get [url]
  (:status (client/get url {:throw-exceptions false})))

(deftest supports-basic-stub-mapping
  (stub { :request {
            :url "/basic/thing"
            :method "GET"
          }
          :response {
            :status 200
            :body "Some text"
          }
    })
  (let [response (client/get (abs-url "/basic/thing") {:throw-exceptions false})]
    (is (= 200 (:status response)))
    (is (= "Some text" (:body response)))))

(deftest supports-reset
  (stub { :request { :method "GET" :url "/to/be/reset" } :response { :status 200 }})
  (is (= 200 (status-for-get (abs-url "/to/be/reset"))))
  (reset)
  (is (= 404 (status-for-get (abs-url "/to/be/reset")))))