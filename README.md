# Weather Data Analysis Project

## Group Members
- **Manan Shah** – ms3452
- **Sneh Vora** – sv992
- **Palak Pabani** – pp872
- **Vinit Kumar Dobariya** – vd363

## Project Overview
This project analyzes hourly weather data from the southeastern region of Brazil to identify patterns in temperature, humidity, wind speed, and precipitation. The main goals of the project are:

1. **Temperature Trends**: Analyzing monthly and seasonal variations in dry bulb temperature.
2. **Humidity and Dew Point**: Investigating the variations in humidity and dew point over time, and their correlation with temperature.
3. **Wind Patterns**: Identifying dominant wind directions and speeds, and analyzing their spatial and temporal variations.

## Dataset Overview
The dataset is sourced from Kaggle and titled "Hourly Weather Surface Brazil Southeast Region." The dataset contains hourly weather data from various weather stations in Brazil's southeastern region. This project focuses on the **north.csv** file, a 1.55 GB subset containing meteorological observations for the northern region of Brazil. The dataset includes various weather parameters such as temperature, humidity, wind speed, and precipitation.

### Dataset Attributes:
- **index**: Row identifier.
- **Data**: Date of the observation.
- **Hora**: Hour of the observation.
- **PRECIPITAÇÃO TOTAL, HORÁRIO (mm)**: Total hourly precipitation.
- **PRESSÃO ATMOSFERICA AO NIVEL DA ESTACAO, HORARIA (mB)**: Atmospheric pressure at the station.
- **RADIACAO GLOBAL (Kj/m²)**: Global radiation.
- **TEMPERATURA DO AR - BULBO SECO, HORARIA (°C)**: Hourly dry bulb air temperature.
- **TEMPERATURA DO PONTO DE ORVALHO (°C)**: Dew point temperature.
- **UMIDADE REL. MAX./MIN. NA HORA ANT. (AUT) (%)**: Max and min relative humidity.
- **VENTO, DIREÇÃO HORARIA (gr)** and **VENTO, VELOCIDADE HORARIA (m/s)**: Wind direction and speed.

## Job Details
### 1. **Temperature Trends Analysis**
Objective: Analyze monthly and seasonal variations in dry bulb temperature.
- **Mapper**: Extracts weather station code and temperature.
- **Reducer**: Computes the average temperature for each station.

### 2. **Total Precipitation & Humidity**
Objective: Analyze variations in humidity, dew point, and their correlation with temperature.
- **Mapper**: Adds humidity and dew point temperature.
- **Reducer**: Calculates average humidity and dew point temperature.

### 3. **Wind Patterns**
Objective: Determine dominant wind directions and speeds.
- **Mapper**: Extracts wind speed data.
- **Reducer**: Identifies the maximum wind speed for each station.

## Oozie Workflow Execution
We successfully installed and configured Oozie to run MapReduce jobs for our project. However, despite challenges, we were able to execute a prebuilt example to confirm the installation was correct. Workflow XML and job properties files are included in the project zip file.

### Performance Measurement
The performance of the MapReduce jobs was measured in response to an increasing number of VMs used for processing the dataset. Here is a summary of job performance as data size increased:

| Data Size (GB) | Job Type              | Time Elapsed (ms) |
|-----------------|-----------------------|-------------------|
| 1.69            | total_precipitation    | 30,974            |
| 1.69            | max_wind               | 29,041            |
| 1.69            | avg_temp               | 29,059            |
| 1.80            | total_precipitation    | 33,512            |
| 1.80            | max_wind               | 31,531            |
| 1.80            | avg_temp               | 32,020            |
| 2.10            | total_precipitation    | 38,003            |
| 2.10            | max_wind               | 36,540            |
| 2.10            | avg_temp               | 37,193            |

## Performance Optimization
- **Job Configuration**: Optimized memory allocation and JVM settings for tasks.
- **Parallel Processing**: Ran independent jobs concurrently to improve resource utilization.
- **Resource Scaling**: Added more VMs to improve job execution speed.
- **Data Handling**: Used efficient file formats (e.g., Parquet, ORC) and partitioned datasets for parallel processing.
- **Error Management**: Implemented retry mechanisms and centralized log collection for troubleshooting.

## Error Handling & Troubleshooting
- **Oozie Environment Setup**: Faced issues with Oozie environment installation due to incorrect commands. Resolved by ensuring proper installation and configuration.
- **Data Format Issues**: Encountered errors with input data. The data was cleaned and validated before processing.
- **Resource Limitations**: Job failures due to memory constraints. Adjusted memory settings in the job configuration to resolve.

## References
- Kumar Ranjan Oozie Installation Guide
- ChatGPT
- Gemini
- Example of Word Count from Canvas

## Setup Instructions
1. Clone the repository.
2. Set up the Hadoop and Oozie environments.
3. Execute the Oozie workflow using the provided XML and job.properties files.
4. Monitor performance by increasing the VM count and dataset size.

Feel free to reach out if you encounter any issues or have questions related to the project.
