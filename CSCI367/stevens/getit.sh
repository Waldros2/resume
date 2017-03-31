echo "###################-START-DOWNLOAD-#####################"
wget http://www.kohala.com/start/unpv12e/unpv12e.tar.gz

# ENSURING NO PERMISSION ISSUES
chmod 777 unpv12e.tar.gz

# UNTAR DOWNLOAD
tar -zxvf unpv12e.tar.gz

# ENSURING NO PERMISSION ISSUES AND GETTING RID OF ANNOYING WRITE-PROTECT
chmod -R 777 unpv12e
rm -r unpv12e.tar.gz

# CONFIGURING ALL MAKEFILES WITH THE 32 BIT CFLAG
cd unpv12e
CFLAGS="-m32" ./configure

# GETTING RID OF ALL DUPLICATE STRUCT OCCURANCES IN unp.h THROUGHOUT ALL SUB DIRECTORIES
find . -type f -iname \unp.h -exec sed -i~ 113,120d '{}' +

# COMPILE LIB
cd lib
make

# COMPILE LIBFREE
cd ../libfree
make
cd ..

echo "####################-DONE-######################"
echo "NOW YOU CAN GO INTO SELECT, SERVER, STREAMS, ETC AND COMPILE INDIVIDUALLY"
echo "################################################"
