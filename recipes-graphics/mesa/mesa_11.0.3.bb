require recipes-graphics/mesa/mesa.inc

SRC_URI = "ftp://ftp.freedesktop.org/pub/mesa/${PV}/mesa-${PV}.tar.xz"

SRC_URI[md5sum] = "bf9118bf0fbf360715cfe60baf7a1db5"
SRC_URI[sha256sum] = "ab2992eece21adc23c398720ef8c6933cb69ea42e1b2611dc09d031e17e033d6"

#because we cannot rely on the fact that all apps will use pkgconfig,
#make eglplatform.h independent of MESA_EGL_NO_X11_HEADER
do_install_append() {
    if ${@bb.utils.contains('PACKAGECONFIG', 'egl', 'true', 'false', d)}; then
        sed -i -e 's/^#if defined(MESA_EGL_NO_X11_HEADERS)$/#if defined(MESA_EGL_NO_X11_HEADERS) || ${@bb.utils.contains('PACKAGECONFIG', 'x11', '0', '1', d)}/' ${D}${includedir}/EGL/eglplatform.h
    fi

    # Workarounds for Hikey board

    #remove EGL
    rm -f ${D}/${libdir}/libEGL*
    #remove GLESv1
    rm -f ${D}/${libdir}/libGLESv1_CM.*
    #remove GLESv2
    rm -f ${D}/${libdir}/libGLESv2.*
    rm -f ${D}/${libdir}/libgbm*
    rm -f ${D}/${libdir}/libwayland-egl*

}


PROVIDES_remove = "virtual/libgles1 virtual/libgles2 virtual/egl"
