### JSON documents differences

When assertion failed, the details of differences will be printed as much as possible.

```
23:30:25.751 [main] DEBUG net.javacrumbs.jsonunit.difference.diff - JSON documents are different:
Array "cases" has different length, expected: <0> but was: <2>.
Array "cases" has different content. Extra values: [1, 2], expected: <[]> but was: <[1,2]>
Different value found in node "name", expected: <"John"> but was: <"John ">.
23:30:25.758 [main] DEBUG net.javacrumbs.jsonunit.difference.values - Comparing expected:
{"name":"John","activated":true,"cases":[]}
------------
with actual:
{"activated":true,"name":"John ","cases":[1,2]}
```