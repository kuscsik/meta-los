SUMMARY = "Weston configuration file with systemd"
DESCRIPTION = "Weston configuration file installation with systemd"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420 "

SRC_URI = " file://weston.ini \
            file://utilities-terminal.png \
            file://chromium-browser.png \
            file://wallpaper_1920x1080.png "

SRC_URI += " file://weston.service \
             file://weston.sh \
             file://weston_profile.sh "

FILES_${PN} = "\
    /tmp/xdg_runtime_dir \
    /home/root/.config/weston.ini \
    /home/root/icon/utilities-terminal.png \
    /home/root/icon/chromium-browser.png \
    /home/root/wallpaper_1920x1080.png "

FILES_${PN} += "/lib/systemd/system/weston.service \
            /usr/sbin/weston.sh \
            /etc/systemd/system/multi-user.target.wants/display-manager.service \
            /etc/profile.d/"

do_install() {
  install -d 0700 ${D}/tmp/xdg_runtime_dir
  install -d ${D}/home/root/.config
  install -d ${D}/home/root/icon
  install -m 0644 ${WORKDIR}/weston.ini ${D}/home/root/.config/weston.ini
  install -m 0644 ${WORKDIR}/utilities-terminal.png ${D}/home/root/icon/utilities-terminal.png
  install -m 0644 ${WORKDIR}/chromium-browser.png ${D}/home/root/icon/chromium-browser.png
  install -m 0644 ${WORKDIR}/wallpaper_1920x1080.png ${D}/home/root/wallpaper_1920x1080.png

  install -d ${D}/lib/systemd/system/ ${D}/usr/sbin
  install -m 0644 ${WORKDIR}/weston.service ${D}/lib/systemd/system/
  install -m 0755  ${WORKDIR}/weston.sh ${D}/usr/sbin

  install -d ${D}/etc/systemd/system/ ${D}/etc/systemd/system/multi-user.target.wants/
  ln -s /lib/systemd/system/weston.service ${D}/etc/systemd/system/multi-user.target.wants/display-manager.service

  install -d ${D}/etc/profile.d
  install -m 0755 ${WORKDIR}/weston_profile.sh ${D}/etc/profile.d/
}
