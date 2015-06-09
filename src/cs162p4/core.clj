(ns cs162p4.core
  (:require [seesaw.core :as s]
            [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [clojure.set :as cset])
  (:gen-class))

(defn center!
  [frame]
  (.setLocationRelativeTo frame nil))



(def file-csv (csv/read-csv (slurp "./resources/testScores.txt")))
(def colwidth 5)
(def new-line (. System getProperty "line.separator"))

(defn average [coll] (/ (apply + coll) (count coll)))

(defn col-space [x] (format (str "%-" colwidth "s") x))

(defn grades-header [num-cols] 
  (str (col-space "St#")
       (apply str (for [i (range num-cols)]
         (col-space (str "T" i))))
       (col-space "Avg")
       (col-space "")
       "Grade"))

(defn letter-grade [percent]
  (condp <= percent
    94 "A"
    90 "A-"
    87 "B+"
    83 "B"
    80 "B-"
    77 "C+"
    73 "C"
    70 "C-"
    67 "D+"
    63 "D"
    60 "D-"
    "F"))

(defn grades-row [col]
  (let [the-average (double (average (map #(Integer. %) col)))
        the-grade   (letter-grade the-average)]
    (str (apply str (map col-space col))
         (col-space the-average)
         (col-space "")
         (col-space the-grade)
         new-line)))

(defn report-grades [col]
  (str (grades-header (count (first col)))
       new-line
       (apply str (map #(str (col-space %1) (grades-row %2)) (range) col))))

(defn -main
  [& args]
  (s/invoke-later
    (doto (s/frame :title "cs162p4"
                   ;;:content "Welcome to cs162p4!"
                   :content (report-grades (rest file-csv))
                   :on-close :exit
                   :size [300 :by 300])
      center!
      s/show!)))