# hex

print and read hex dumps

for convenience fiddling with bits

## usage

just look at the source :)

`print-hexdump` can optionally take a width (in a number of bytes)

```
im.crate.hex=> (print-hexdump (byteslurp "README.md"))
23 20 68 65 78 0a 0a 70 72 69 6e 74 20 61 6e 64 20 72 65 61 | # hex..print and rea
64 20 68 65 78 20 64 75 6d 70 73 0a 0a 66 6f 72 20 63 6f 6e | d hex dumps..for con
76 65 6e 69 65 6e 63 65 20 66 69 64 64 6c 69 6e 67 20 77 69 | venience fiddling wi
74 68 20 62 69 74 73 0a 0a                                  | th bits..
```

```
im.crate.hex=> (String. (read-hexdump-string (print-hexdump-string (byteslurp "README.md"))))
"# hex\n\nprint and read hex dumps\n\nfor convenience fiddling with bits\n\n"
```

