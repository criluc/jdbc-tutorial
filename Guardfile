require 'asciidoctor'
require 'erb'

guard 'shell' do
  watch(/^src/docs/[^#]+\.adoc$/) {|m|
    Asciidoctor.convert_file(m[0], :in_place => true,
      :template_dirs => ['../asciidoctor-reveal.js/templates/slim'])
  }
end