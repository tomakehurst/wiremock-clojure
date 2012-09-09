(defproject wiremock-clojure-client "1.0-SNAPSHOT"
  :description "A Clojure client library for WireMock"
  :dependencies [[org.clojure/clojure "1.3.0"]
                 [woven/clj-http "0.1.2-SNAPSHOT"]
                 [clj-json "0.5.1"]
                 [org.clojure/data.json "0.1.2"]
                 [com.github.tomakehurst/wiremock "1.24" :classifier "standalone"]])