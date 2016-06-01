SUMMARY = "Mali400 libraries (drm backend)"
DESCRIPTION = "Mali400 libraries (opengles, egl...)"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://${WORKDIR}/END_USER_LICENCE_AGREEMENT.txt;md5=3918cc9836ad038c5a090a0280233eea"

SRC_URI[md5sum] = "36f39e86ccfe5a6a4cb2090865c339ba"
SRC_URI[sha256sum] = "dd136931cdbb309c0ce30297c06f7c6b0a48450f51acbbbc10529d341977f728"

PROVIDES = "mali450-userland virtual/egl virtual/libgles1 virtual/libgles2"
PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "libdrm mesa"

PV_MALI="r6p0"
PR_MALI="01rel0"

PR="${PR_MALI}.binary"

BACKEND="drm"

SRC_URI = " http://malideveloper.arm.com/downloads/drivers/binary/utgard/r6p0-01rel0/mali-450_r6p0-01rel0_linux_1+arm64.tar.gz;destsuffix=mali"

S = "${WORKDIR}/wayland-drm"

#action stubbed
do_configure[noexec] = "1"
do_compile[noexec] = "1"


do_install() {
    install -m 755 -d ${D}/usr/lib
    cp -aR ${S}/* ${D}/usr/lib
}

PACKAGES = "${PN} ${PN}-dev ${PN}-dbg ${PN}-test "

INSANE_SKIP_${PN} = "dev-so"
FILES_SOLIBSDEV = ""

FILES_${PN}            += "${libdir}/libMali.so* ${libdir}/*.so"
FILES_${PN}-dev        += "${includedir} ${libdir}/pkgconfig/"
FILES_${PN}-dbg        += "${libdir}/.debug/ ${bindir}/.debug/ "

FILES_${PN}-test        = "${exec_prefix}/share/gles2_test/ ${exec_prefix}/share/egl_test "
FILES_${PN}-test       += "${exec_prefix}/bin/platform_test_suite"
FILES_${PN}-test       += "${exec_prefix}/bin/egl_api_main_suite_20"
FILES_${PN}-test       += "${exec_prefix}/bin/gles2_api_suite"

RREPLACES_${PN}       = "libglesv2-2 "
RPROVIDES_${PN}       = "libglesv2-2 "
RCONFLICTS_${PN}      = "libglesv2-2 "


# For the packages that make up the OpenGL interfaces, inject variables so that
# they don't get Debian-renamed (which would remove the -mali450 suffix), and
# RPROVIDEs/RCONFLICTs on the generic libgl name.
python __anonymous() {
    pkgconfig = (d.getVar('PACKAGECONFIG', True) or "").split()
    for p in (("libegl"), ("libegl1"),
              ("gles"), ("libgles1"), ("libglesv1-cm1"),
              ("libgles2"), ("mali450-userland")):
        fullp = "mali450-userland"
        pkgs = " "  + p + " "
        d.setVar("DEBIAN_NOAUTONAME_" + fullp, "1")
        d.appendVar("RREPLACES_" + fullp, pkgs)
        d.appendVar("RPROVIDES_" + fullp, pkgs)
        d.appendVar("RCONFLICTS_" + fullp, pkgs)

        # For -dev, the first element is both the Debian and original name
        fullp += "mali450-userland-dev"
        pkgs = " "  + p + "-dev "
        d.setVar("DEBIAN_NOAUTONAME_" + fullp, "1")
        d.appendVar("RREPLACES_" + fullp, pkgs)
        d.appendVar("RPROVIDES_" + fullp, pkgs)
        d.appendVar("RCONFLICTS_" + fullp, pkgs)
}

INSANE_SKIP_${PN} += "ldflags"

