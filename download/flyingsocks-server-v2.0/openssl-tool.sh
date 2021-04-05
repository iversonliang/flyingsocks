#! /bin/bash
if command -v openssl > /dev/null 2>&1; then
    echo "openssl exists, ready to build cert"
else
    yum install -y openssl openssl-devel
fi

if [ ! -n "$1" ]; then
    CERT_PATH = ./encrypt
    if [ ! -d "./cert" ]; then
        mkdir encrypt
    fi
else
    CERT_PATH=$1
fi


cd $CERT_PATH

openssl req -new -nodes -x509 -keyout ca.key -out ca.crt
unset CERT_PATH
cd ..
echo "Done"