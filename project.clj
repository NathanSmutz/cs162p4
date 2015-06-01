(defproject cs162p4 "0.0.1-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [seesaw "1.4.5"]
                 [org.clojure/data.csv "0.1.2"]]
  :javac-options ["-target" "1.6" "-source" "1.6" "-Xlint:-options"]
  :aot [cs162p4.core]
  :main cs162p4.core)
