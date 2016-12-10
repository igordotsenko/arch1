#!/usr/bin/env bash

input_directory=$1
remote_path=$2
war_name=$3

temp_dir="${input_directory}/tmp"
output_path=${temp_dir}/${war_name}.war

mkdir ${temp_dir} 
jar cfvM ${output_path} -C ${input_directory} .
scp ${output_path} ${remote_path}
rm -r ${temp_dir}

