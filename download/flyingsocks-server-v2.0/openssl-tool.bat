echo "OpenSSL Tools"
md encrypt
cd encrypt
openssl req -new -nodes -x509 -keyout ca.key -out ca.crt
pause