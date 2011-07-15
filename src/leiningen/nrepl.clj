(ns leiningen.nrepl
  (:use [leiningen.compile :only [eval-in-project]]))

(defn nrepl
  "Launch an nrepl server."
  ([project port]
   (let [p (Integer. port)]
     (eval-in-project
       project
       `(~'do
            (~'println "Starting nREPL on port:" ~p )
            (~'clojure.tools.nrepl/start-server ~p)
            (~'println "Running.")
            (~'Thread/sleep Long/MAX_VALUE))
       nil
       nil
       `(require 'clojure.tools.nrepl)
       )))
  ([project] (nrepl project 11011)))
