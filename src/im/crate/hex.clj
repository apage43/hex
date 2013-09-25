(ns im.crate.hex
  (:require [clojure.java.io :as io]
            [clojure.string]))

(defn- printable? [n]
  (and (>= n 0x20)
       (<= n 0x7E)))

(defn byteslurp [filename]
  (let [baos (java.io.ByteArrayOutputStream.)]
    (io/copy (io/file filename) baos)
    (.toByteArray baos)))

(defn print-hexdump
  ([byteseq options]
   (let [width (or (:width options) 20)
         chunks (partition-all width byteseq)]
     (doseq [ch chunks]
       (doseq [b ch]
         (print (format "%02x " (byte b))))
       (dotimes [_ (- width (count ch))] (print "   "))
       (print "| ")
       (doseq [b ch]
         (print (if (printable? b) (char b) ".")))
       (println))))
   ([byteseq]
    (print-hexdump byteseq {})))

(defn read-hexdump [reader]
  (let [baos (java.io.ByteArrayOutputStream.)
        lines (line-seq reader)]
    (doseq [line lines]
      (let [hexes (clojure.string/split line #"\s+")
            hexes (take-while (partial not= "|") hexes)]
        (doseq [hex hexes]
          (.write baos (Integer/parseInt hex 16)))))
    (.toByteArray baos)))

(defn read-hexdump-string [string]
  (read-hexdump (io/reader (.getBytes string))))

(defn print-hexdump-string
  ([byteseq options]
   (with-out-str (print-hexdump byteseq options)))
  ([byteseq]
   (print-hexdump-string byteseq {})))

