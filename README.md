gettext
=======

[GNU Gettext](https://www.gnu.org/software/gettext) for Clojure based on
 [Easy i18n](https://github.com/awkay/easy-i18n).

Gettext library is just a thin wrapper around Easy i18n for ease usage.

[![Dependencies Status](https://jarkeeper.com/druids/gettext/status.png)](https://jarkeeper.com/druids/gettext)
[![License](https://img.shields.io/badge/MIT-Clause-blue.svg)](https://opensource.org/licenses/MIT)


Leiningen/Boot
--------------

To install, add the following to you project `:dependencies`:

```clojure
[gettext "0.0.0"]
```

Requirements
------------

Install a system dependency [GNU Gettext](https://www.gnu.org/software/gettext).


Usage
-----

`gettext.core` namespace provides following functions:

- `tr` a basic translate function
- `trc` translates a string in the given context
- `trf` a basic translate function within format abilities
- `trcf` translate a message with context and arguments
- `trp` formats a string that varies based on plural forms


Examples:

```clojure
(require '[gettext.core :as gettext])

(gettext/tr "Hello") ;; Hello
(gettext/tr :de "Hello") ;; "Hallo"

(gettext/trf "Hello {0}" ["world"]) ;; "Hello world"
(gettext/trf :de "Hello {0}" ["Das Welt"]) ;; "Hallo Das Welt"
```
