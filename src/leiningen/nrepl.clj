(ns leiningen.nrepl
  (:use [leiningen.compile :only [eval-in-project]]))

(defn nrepl
  "Launch an nrepl server."
  ([project port]
   (let [p (Integer. port)]
     (eval-in-project
       project
       `(~'do
            (println "Starting nREPL on port:" ~p )
            (let [[ss# _#] (~'clojure.tools.nrepl/start-server ~p)]
              (~'println "Running.")
              (let [connection# (~'clojure.tools.nrepl/connect "localhost" (.getLocalPort ss#))
                    prompt#     #(print (str % "=> "))
                    err#        print
                    out#        print
                    value#      print ]
                (println "network-repl")
                (println (str "Clojure " (clojure-version)))
                (loop [ns# "user"]
                  (prompt# ns#)
                  (flush)
                  (recur (last
                           (for [res# (clojure.tools.nrepl/response-seq ((:send connection#) (pr-str (read))))]
                             (do
                               (when (:value res#) (value# (:value res#)))
                               (when (:out res#) (out# (:out res#)))
                               (when (:err res#) (err# (:err res#)))
                               (:ns res#))))))))
            (~'Thread/sleep Long/MAX_VALUE))
       nil
       nil
       '(require 'clojure.tools.nrepl)
       )))
  ([project] (nrepl project 11011)))
