(ns gettext.core
  (:import
    com.teamunify.i18n.I
    gnu.gettext.GettextResource))


(defn tr
  "Translate a literal message to the user's current language. This method is the most efficient to use, as it merely
    needs to look up the translation, but need not apply any extra formatting."
  ([msgid]
   {:pre [(some? msgid)]}
   (I/tr msgid))
  ([lang msgid]
   {:pre [(some? lang) (some? msgid)]}
   (let [current-lang (-> (I/getCurrentLanguage) .-locale)
         _ (I/setLanguage (name lang))
         msg (I/tr msgid)
         _ (I/setLanguage current-lang)]
     msg)))


(defn trc
  "Translate a string in the given context. Useful for resolving the possible differences in things like single words
    (e.g. noun vs. verb form)."
  ([context msgid]
   {:pre [(some? context) (some? msgid)]}
   (I/trc context msgid))
  ([lang context msgid]
   {:pre [(some? lang) (some? context) (some? msgid)]}
   (let [current-lang (-> (I/getCurrentLanguage) .-locale)
         _ (I/setLanguage (name lang))
         msg (I/trc context msgid)
         _ (I/setLanguage current-lang)]
     msg)))


(defn trf
  "Translate a message that includes placeholders to format.
   This function translates the text, then uses java.text.MessageFormat to do the actual parameter substitution."
  ([msgid args]
   {:pre [(some? msgid)]}
   (I/trf msgid (into-array Object args)))
  ([lang msgid args]
   {:pre [(some? lang) (some? msgid)]}
   (let [current-lang (-> (I/getCurrentLanguage) .-locale)
         _ (I/setLanguage (name lang))
         msg (I/trf msgid (into-array Object args))
         _ (I/setLanguage current-lang)]
     msg)))


(defn trcf
  "Translate a message with context and arguments."
  ([context msgid args]
   {:pre [(some? msgid)]}
   (I/trcf context msgid (into-array Object args)))
  ([lang context msgid args]
   {:pre [(some? lang) (some? msgid)]}
   (let [current-lang (-> (I/getCurrentLanguage) .-locale)
         _ (I/setLanguage (name lang))
         msg (I/trcf context msgid (into-array Object args))
         _ (I/setLanguage current-lang)]
     msg)))


(defn trp
  "Format a string that varies based on plural forms."
  ([singular plural nitems args]
   {:pre [(some? singular) (some? plural) (some? nitems)]}
   (I/tr_plural singular plural nitems (into-array Object args)))
  ([lang singular plural nitems args]
   {:pre [(some? lang) (some? singular) (some? plural) (some? nitems)]}
   (let [current-lang (-> (I/getCurrentLanguage) .-locale)
         _ (I/setLanguage (name lang))
         msg (I/tr_plural singular plural nitems (into-array Object args))
         _ (I/setLanguage current-lang)]
     msg)))
