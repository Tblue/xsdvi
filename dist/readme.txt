|--------|
| Usage: |
|--------|

java -jar xsdvi.jar <input1.xsd> [<input2.xsd> [<input3.xsd> ...]] [style]
  STYLE:
    -embodyStyle                css style will be embodied in each svg file, this is default
    -generateStyle <style.css>  new css file with specified name will be generated and used by svgs
    -useStyle      <style.css>  external css file at specified url will be used by svgs