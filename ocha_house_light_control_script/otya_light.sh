#!/bin/bash
rm -f call*
for ((i=0;i<2;++i))
do
wget "http://192.168.1.142:31413/call.json?method=set&params=[GeneralLighting1,[0x80,0x30]]"
sleep 1000
wget "http://192.168.1.142:31413/call.json?method=set&params=[GeneralLighting2,[0x80,0x30]]"
sleep 1000 
wget "http://192.168.1.142:31413/call.json?method=set&params=[GeneralLighting3,[0x80,0x30]]"
sleep 1000
wget "http://192.168.1.142:31413/call.json?method=set&params=[GeneralLighting4,[0x80,0x30]]"
sleep 1000
wget "http://192.168.1.142:31413/call.json?method=set&params=[GeneralLighting1,[0x80,0x31]]"
sleep 1000
wget "http://192.168.1.142:31413/call.json?method=set&params=[GeneralLighting2,[0x80,0x31]]"
sleep 1000
wget "http://192.168.1.142:31413/call.json?method=set&params=[GeneralLighting3,[0x80,0x31]]"
sleep 1000
wget "http://192.168.1.142:31413/call.json?method=set&params=[GeneralLighting4,[0x80,0x31]]"
sleep 1000
done


