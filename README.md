# lein-nrepl

nREPL plugin for leiningen.

Work in progress. Starts nREPL server for project, and opens up client connection to that server.

## Installation

Add to project.clj:

:dev-dependencies [[org.pretendcow/lein-nrepl "0.0.1"]]

## Usage

lein trampoline nrepl

NOTE: needs to be trampolined at this stage, lein has issues with stdin

## License

Copyright (C) 2011 Steve Lindsay

Distributed under the Eclipse Public License, the same as Clojure.
