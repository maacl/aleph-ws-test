(defproject aleph-ws-test "0.0.1-SNAPSHOT"
  :description "Test aleph websocket support"
  :namespaces [aleph-ws-test.core
	       aleph-ws-test.server]
  :dependencies [[org.clojure/clojure "1.2.0"]
                 [org.clojure/clojure-contrib "1.2.0"]
		 [aleph "0.1.0-SNAPSHOT"]]
  :dev-dependencies [[swank-clojure "1.2.1"]
		     [lein-run "1.0.0-SNAPSHOT"]]
  :run-aliases {:server [aleph-ws-test.server -main]})