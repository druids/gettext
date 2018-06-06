(defproject gettext "0.0.0"
  :description "GNU Gettext for Clojure based on Easy I18N"
  :url "https://github.com/druids/gettext"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"}

  :dependencies [[com.teamunify/easy-i18n "0.9.1"]]

  :eval-in-leiningen true

  :profiles {:dev {:plugins [[lein-kibit "0.1.6"]
                             [jonase/eastwood "0.2.5"]
                             [venantius/ultra "0.5.2"]]
                   :dependencies [[org.clojure/clojure "1.9.0"]]
                   :source-paths ["src" "dev/src"]}
             :test {:dependencies [[org.clojure/clojure "1.9.0"]]}})
