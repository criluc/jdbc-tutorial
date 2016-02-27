= Setup di asciidoctor

In shell:
 gem install --user-install asciidoctor tilt thread_safe slim
 cd ~/git # o directory qualsiasi
 git clone git://github.com/asciidoctor/asciidoctor-reveal.js.git

In questo progetto (o simile):
 curl -L https://github.com/hakimel/reveal.js/archive/3.2.0.tar.gz | tar xzf -
 mv reveal.js{-3.2.0,}

== Per compilare

 asciidoctor -T ~/git/asciidoctor-reveal.js/templates/slim jpa-tutorial.txt

== Setup emacs

$ cat >> .emacs <<EOF
(when (>= emacs-major-version 24)
  (require 'package)
  (add-to-list
   'package-archives
   '("melpa" . "http://melpa.org/packages/")
   t)
  (package-initialize))
(setq-default fill-column 78)
EOF

In emacs:
 M-x package-install RET adoc-mode RET

E nel buffer specifico poi (jpa-tutorial.txt):
 M-x adoc-mode

== Auto ri-compilazione via gradle

./gradlew asciidoctor --continuous

=== Auto compilazione via guard

Auto ricompila su ogni modifica dei file .adoc il corrispondente .html:

 gem install guard guard-shell
 guard start
