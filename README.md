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
URI: https://github.com/ndechesne/meta-qcom.git
layers: meta-qcom
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


## Hikey instructions

Checkout the hikey branch of the project:

https://github.com/kuscsik/meta-96boards


Get the graphics drivers from here 


https://drive.google.com/a/linaro.org/file/d/0B8Uq4Q7WAxO4ZjJLdGJQR01DRkE/view?usp=sharing

and place the tar package in the following folder:

```
~/Public/oe-downloads/
```

Build the image:

```
$ source ./meta-los/script/envsetup
```

Edit the conf/local.conf file and change the MACHINE value to "hikey". 

```
$ bitbake los-weston-image
```

Convert the ext4 image to a fastboot sparse image

```
$ ext2simg los-weston-image-hikey.ext4 los-weston-image-hikey.img
```

and flash it using fastboot

```
$ fastboot flash system los-weston-image-hikey.img
```

Get the boot-fat.uefi.img.gz from here:

https://builds.96boards.org/snapshots/hikey/linaro/debian/latest/https://builds.96boards.org/snapshots/hikey/linaro/debian/latest/ 

And replace the default grub.cfg with the one from meta-los:

```
$ mkdir -p boot-fat
$ sudo mount -o loop,rw,sync boot-fat.uefi.img boot-fat
$ cp conf/grub.cfg boot-fat/EFI/BOOT/
$ sync
$ sudo umount boot-fat.uefi.img
$ fastboot flash boot boot-fat.uefi.img
```

Important: Don't forget to unplug the OTG cable after flashing is done and connect a mouse/keyboard
to the board. Remove any SD card if present.

The grub.cfg is configured to 1920x1080@25 resolution, if that doesn't works for you for some reason,
try to edit the file and set it to a safe 800x600@60 resolution first.

Reboot.

