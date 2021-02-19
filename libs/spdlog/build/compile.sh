

ABI=x86

ANDROID_NDK=home/daneel/Android/Sdk/ndk/21.1.6352462
TOOL_CHAIN=/home/daneel/Android/Sdk/ndk/21.1.6352462/build/cmake/android.toolchain.cmake
CMAKE=/home/daneel/Android/Sdk/cmake/3.10.2.4988404/bin/cmake

mkdir -p ${ABI}
cd ${ABI}

${CMAKE} ../../spdlog -DCMAKE_SYSTEM_NAME=Android -DCMAKE_SYSTEM_VERSION=21 \
-DANDROID_ABI=${ABI} -DCMAKE_TOOLCHAIN_FILE=${TOOL_CHAIN}

${CMAKE} --build .
