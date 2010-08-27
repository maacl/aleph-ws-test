(ns aleph-ws-test.core
  [:use aleph.core.channel aleph.formats aleph.http])

(def kanaler (atom #{}))

(defn rek [msg]
  (println @kanaler)
  (doall
   (for [k @kanaler]
     (if (closed? k)
       (swap! kanaler disj k)
       (enqueue k msg)))))

(defn event-loop [channel opt]
  (swap! kanaler conj channel)
  (receive-all channel rek))

(defn start-server [port]
  (start-http-server event-loop {:port port :websocket true}))