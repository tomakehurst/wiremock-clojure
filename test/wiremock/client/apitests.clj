(ns wiremock.client.apitests
  (:use [wiremock.client])
  (:use [clojure.test])
  (:require [clj-http.client :as client]))

(use-fixtures :each wiremock-single-test-fixture)

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
  (let [response (client/get "http://localhost:8080/basic/thing" {:throw-exceptions false})]
    (is (= 200 (:status response)))
    (is "Some text" (:body response))))

