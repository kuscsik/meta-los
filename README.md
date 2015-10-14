# meta-los

## Introduction

OpenEmbedded/Yocto Project layer to add some wayland/weston ui.
los: Linaro OpenSdk

This layer depends on:

```
URI: git://git.openembedded.org/openembedded-core.git
layers: meta
branch: master
revision: HEAD
```

```
URI: git://git.openembedded.org/bitbake
branch: master
revision: HEAD
```

```
URI: git://git.openembedded.org/meta-openembedded
layers: meta-filesystems, meta-gnome, meta-multimedia, 
        meta-oe, meta-systemd, ...
branch: master
revision: HEAD
```

```
URI: https://github.com/ndechesne/meta-qcomm.git
layers: meta-qcomm
branch: jethro
revision: HEAD
```

```
URI: https://github.com/linaro-home/meta-browser.git
layers: meta-browser
branch: chromium_45
revision: HEAD
```



This layer:

```
URI: https://github.com/cpriouzeau/meta-los.git
layers: meta-los
branch: master
revision: HEAD
```

## how to setup environment

source ./meta-los/script/envsetup
bitbake los-weston-image

## Warning
To get the firmware of Qualcomm dragonboard, you need to accept the EULA.
For accepting it, we have added the following line on local.conf:
ACCEPT_QCOM_EULA = "1"


## How to set an environment of zero
mkdir oe_dradonboard
cd oe_dragonboard
git clone https://github.com/cpriouzeau/meta-los.git
./meta-los/script/get-layer.sh

source ./meta-los/script/envsetup
bitbake los-weston-image

