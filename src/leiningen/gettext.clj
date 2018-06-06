(ns leiningen.gettext
  (:require
    [clojure.java.io :as io]
    [clojure.java.shell :as shell]
    [clojure.string :refer [join]]
    [leiningen.core.main :refer [info warn]])
  (:import
    java.io.File))


(def path-join (partial join File/separator))


(defn sh
  [& args]
  (let [result (apply shell/sh args)]
    (when (-> result :err some?)
      (warn (:err result)))))


(defn gettext-dir
  [project]
  (if (-> project :gettext :target-dir some?)
    (-> project :gettext :target-dir)
    (path-join ["resources" (-> project :name str) "i18n"])))


(defn gettext-init
  [project languages]
  (let [target-dir (gettext-dir project)
        pot-file-path (path-join [target-dir "messages.pot"])]
    (when-not (-> pot-file-path io/as-file .exists)
      (sh "touch" pot-file-path))

    (doseq [lang languages]
      (let [file-path (path-join [target-dir (format "%s.po" lang)])]
        (info (format "Initializing template file for %s" lang))
        (if (-> file-path io/as-file .exists)
          (warn (format "%s exists! You probably wanted merge, skipping." file-path))
          (sh "msginit" "--no-translator" "-l" lang "--no-wrap" "-o" file-path "-i" pot-file-path))))))


(defn gettext-merge
  [project languages]
  (let [target-dir (gettext-dir project)
        pot-file-path (path-join [target-dir "messages.pot"])]
    (doseq [lang languages]
      (let [file-path (path-join [target-dir (format "%s.po" lang)])]
        (info (format "Merging template file into %s" lang))
        (if (-> file-path io/as-file .exists)
          (sh "msgmerge" "-i" "-F" "--no-wrap" "-o" file-path file-path pot-file-path)
          (warn (format "%s does not exist! Skipping." file-path)))))))


(defn gettext-compile
  [project languages]
  (let [target-dir (gettext-dir project)
        pot-file-path (path-join [target-dir "messages.pot"])
        target-ns "com.teamunify.i18n.messages"]
    (sh "mkdir" "-p" "target/classes")
    (doseq [lang languages]
      (let [file-path (path-join [target-dir (format "%s.po" lang)])]
        (info (format "Compiling %s to package %s" lang target-ns))
        (sh "msgfmt" "--verbose" "-f" "-r" target-ns "--java2" "-d" "target/classes" "-l" lang file-path)))))


(defn gettext
  [project & [command & languages]]
  (if (empty? languages)
    (warn "At least one language is needed.")
    (case command
      "init" (gettext-init project languages)
      "merge" (gettext-merge project languages)
      "compile" (gettext-compile project languages)
      (warn (format "Unknown Gettext command: %s" command)))))
