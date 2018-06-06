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
[druids/gettext "0.1.0"]
```

And into `:plugins`, if you want to call leiningen tasks.


Requirements
------------

Install a system dependency [GNU Gettext](https://www.gnu.org/software/gettext).


Configuration
-------------

A path for message files can be specified in `project.clj` like:

```clojure
{:gettext {:target-dir "resources/myproject/i18n"}
```

When the path is not specified following path is used: "resources/PROJECT\_NAME/i18n".


Usage
-----

Before you start a language file need to be initialized, e.g.:

```sh
lein gettext init cs ge
```

When translations are done, messages need to be merged:

```sh
lein gettext merge cs ge
```

And compiled into Java classes:

```sh
lein gettext compile cs ge
```


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


Notes
-----

Currently extracting messages from Clojure code is **NOT** supported. This job has to be done by a hand.
