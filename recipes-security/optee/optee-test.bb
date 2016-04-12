SUMMARY = "OPTEE Test"
DESCRIPTION = "OPTEE Test"

LICENSE = "CLOSED"
PR="r0"
PV="2.0+git"

DEPENDS = "optee-os optee-client"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "git://github.com/OP-TEE/optee_test;protocol=https \
           file://0001-Silence-may-be-unset-CLang-warning.patch \
           "
SRCREV = "706729799925d680f8f915a812063369bfee897a"

#patch
S = "${WORKDIR}/git"
B = "${S}"

#stub
do_configure[noexec] = "1"

EXTRA_OEMAKE = ""
do_compile() {
    export TA_DEV_KIT_DIR=${STAGING_INCDIR}/optee/export-user_ta
    export TEEC_EXPORT=${STAGING_DIR_HOST}/usr

    export OPTEE_CLIENT_EXPORT=${STAGING_DIR_HOST}/usr
    oe_runmake  V=1 CROSS_COMPILE_HOST=${HOST_PREFIX} CROSS_COMPILE_TA=${HOST_PREFIX}
}

do_install() {
    mkdir -p ${D}/usr/bin
    mkdir -p ${D}/lib/optee_armtz

    install -m 544 ${B}/out/xtest/xtest ${D}/usr/bin/xtest

    install -m 444 ${B}/out/ta/concurrent/e13010e0-2ae1-11e5-896a0002a5d5c51b.ta ${D}/lib/optee_armtz/
    install -m 444 ${B}/out/ta/rpc_test/d17f73a0-36ef-11e1-984a0002a5d5c51b.ta ${D}/lib/optee_armtz/
    install -m 444 ${B}/out/ta/create_fail_test/c3f6e2c0-3548-11e1-b86c0800200c9a66.ta ${D}/lib/optee_armtz/
    install -m 444 ${B}/out/ta/concurrent_large/5ce0c432-0ab0-40e5-a056782ca0e6aba2.ta ${D}/lib/optee_armtz/
    install -m 444 ${B}/out/ta/storage/b689f2a7-8adf-477a-9f9932e90c0ad0a2.ta ${D}/lib/optee_armtz/
    install -m 444 ${B}/out/ta/sims/e6a33ed4-562b-463a-bb7eff5e15a493c8.ta ${D}/lib/optee_armtz/
    install -m 444 ${B}/out/ta/os_test/5b9e0e40-2636-11e1-ad9e0002a5d5c51b.ta ${D}/lib/optee_armtz/
    install -m 444 ${B}/out/ta/storage_benchmark/f157cda0-550c-11e5-a6fa0002a5d5c51b.ta ${D}/lib/optee_armtz/
    install -m 444 ${B}/out/ta/crypt/cb3e5ba0-adf1-11e0-998b0002a5d5c51b.ta ${D}/lib/optee_armtz/
}

FILES_${PN} = "/usr/bin/ /lib/optee_armtz/"
INSANE_SKIP_${PN}-dev = "staticdev"

INHIBIT_PACKAGE_STRIP = "1"
