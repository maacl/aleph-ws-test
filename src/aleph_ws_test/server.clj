(ns aleph-ws-test.server
  [:use aleph-ws-test.core])

(defn -main [& [port]]
  (if port
    (start-server (Integer/parseInt port))
    (start-server 10000)))