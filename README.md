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
layers: meta
branch: master
revision: HEAD
```

```
URI: git://git.openembedded.org/meta-openembedded
layers: meta
branch: master
revision: HEAD
```

```
URI: https://github.com/ndechesne/meta-qualcomm.git
layers: meta
branch: jethro
revision: HEAD
```

This layer:

```
URI: https://github.com/cpriouzeau/meta-los.git
layers: meta
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

