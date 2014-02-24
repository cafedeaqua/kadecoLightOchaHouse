#!/bin/bash
rm -f call*

change_contrast(){
# 100%
wget "http://192.168.1.142:31413/call.json?method=set&params=[GeneralLighting,[0xB0,0x64]]"
# 050%
#wget "http://192.168.1.142:31413/call.json?method=set&params=[GeneralLighting,[0xB0,0x32]]"
# 000%
wget "http://192.168.1.142:31413/call.json?method=set&params=[GeneralLighting,[0xB0,0x00]]"
return 0	
}

for ((i=0;i<1000;++i))
do
#ON
wget "http://192.168.1.142:31413/call.json?method=set&params=[GeneralLighting,[0x80,0x30]]"
# 100%
wget "http://192.168.1.142:31413/call.json?method=set&params=[GeneralLighting,[0xB0,0x64]]"
# 0x42 white
wget "http://192.168.1.142:31413/call.json?method=set&params=[GeneralLighting,[0xB6,0x42]]"
change_contrast

#0x43 red
wget "http://192.168.1.142:31413/call.json?method=set&params=[GeneralLighting,[0xB6,0x43]]"
change_contrast

#0x41 murasaki 
wget "http://192.168.1.142:31413/call.json?method=set&params=[GeneralLighting,[0xB6,0x41]]"
change_contrast

#0x45 yellow
wget "http://192.168.1.142:31413/call.json?method=set&params=[GeneralLighting,[0xB6,0x45]]"
change_contrast

#OFF
wget "http://192.168.1.142:31413/call.json?method=set&params=[GeneralLighting,[0x80,0x31]]"

done
