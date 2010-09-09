(ns aleph-ws-test.core
  [:use aleph.core.channel aleph.formats aleph.http aleph.tcp])

(def kanaler (atom #{}))

(defn rek [msg]
  (println msg)
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

(def *spf* "<cross-domain-policy><allow-access-from domain='*' to-ports='*'/></cross-domain-policy>\n\n\0")

(defn spf-handler [channel connection-info]
  (receive-all channel (fn [req]
			 (if (= "<policy-file-request/>\0"
				(byte-buffer->string req))
			   (let [f (string->byte-buffer *spf*)]
			     (enqueue-and-close channel f))
			   (println (byte-buffer->string req))))))

(defn start-policy-server []
  (start-tcp-server spf-handler {:port 843}))
