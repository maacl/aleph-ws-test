(ns aleph-ws-test.server
  [:use aleph-ws-test.core])

(defn -main [& [port]]
  (start-server 10000)
  (start-policy-server)
)
