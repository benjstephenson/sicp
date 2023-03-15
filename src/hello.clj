(ns hello
  (:require [java-time.api :as t]))

(defn time-str [instant]
  (t/format
   (t/with-zone (t/formatter "hh:mm a") (t/zone-id))
   instant))

(defn run [opts]
  (println "Hello world, the time is" (time-str (t/instant))))

(run [])

