Mandelbrot {
; Mandelbrot Set
init:
    z = 0
    c = #pixel
loop:
    z = z*z + c
bailout:
    |z| < 4.0
}
