DESCRIPTION = "Linaro OpenSDK image."

PV = "1.0.0"
PR = "r1"

LICENSE = "MIT"

#
# Multimedia part addons
#
IMAGE_MM_PART = " \
    packagegroup-gstreamer1-0 \
    gstreamer1.0-plugins-v4l2 \
    tiff \
    libv4l \
    rc-keymaps \
"

#
# Audio part addons
#
IMAGE_AUDIO_PART = " \
    alsa-lib \
    alsa-utils \
"

#
#
#
IMAGE_BROWSER = " \
    libexif \
    chromium \
"

IMAGE_INSTALL += " \
    ${IMAGE_MM_PART} \
    ${IMAGE_AUDIO_PART} \
    ${IMAGE_BROWSER} \
"
include recipes-core/images/los-weston-image.bb

