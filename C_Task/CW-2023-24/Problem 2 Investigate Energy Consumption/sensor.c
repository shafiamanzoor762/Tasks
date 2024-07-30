
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define HOURS_PER_DAY 24
#define NUM_SENSOR_FILES 3
#define NUM_MOTION_SENSORS 6
#define HEATING_CONSUMPTION 150
#define VENTILATION_CONSUMPTION 125
#define LIGHTS_CONSUMPTION 100

// Structure to hold sensor data for each hour
typedef struct {
    char timestamp[10];
    float outside_temp;
    float inside_temp;
    float humidity;
    float motion_sensors[NUM_MOTION_SENSORS];
} SensorData;

// Function to read data from sensor files
void readDataFile(char *location, SensorData data[HOURS_PER_DAY]) {
    FILE *fp = fopen(location, "r");
    if (fp == NULL) {
        printf("Error opening file %s\n", location);
        exit(1);
    }
    for (int t = 0; t < HOURS_PER_DAY && !feof(fp); t++) {
        fscanf(fp, "%s", data[t].timestamp);
        fscanf(fp, "%f%f", &data[t].outside_temp, &data[t].inside_temp);
        fscanf(fp, "%f", &data[t].humidity);
        for (int k = 0; k < NUM_MOTION_SENSORS; k++)
            fscanf(fp, "%f", &data[t].motion_sensors[k]);
    }
    fclose(fp);
}

// Function to control the state of devices based on user's specifications
void controlDevices(SensorData data[HOURS_PER_DAY], int threshold_heating, int threshold_ventilation, int threshold_lights) {
    // Implement device control logic here
    // For simplicity, let's just print the state of devices based on thresholds
    printf("Device Control:\n");
    printf("Heating: ");
    if (data->inside_temp < threshold_heating)
        printf("ON\n");
    else
        printf("OFF\n");

    printf("Ventilation: ");
    if (data->humidity > threshold_ventilation)
        printf("ON\n");
    else
        printf("OFF\n");

    printf("Lights: ");
    int motion_detected = 0;
    for (int i = 0; i < NUM_MOTION_SENSORS; i++) {
        if (data->motion_sensors[i] > 0) {
            motion_detected = 1;
            break;
        }
    }
    if (motion_detected)
        printf("ON\n");
    else
        printf("OFF\n");
}

void calculateEnergyConsumption(SensorData data[HOURS_PER_DAY], int threshold_heating, int threshold_ventilation, int threshold_lights) {
    int total_heating_units = 0;
    int total_ventilation_units = 0;
    int total_lights_units = 0;

    // Calculate total units for each device
    for (int i = 0; i < HOURS_PER_DAY; i++) {
        if (data[i].inside_temp < threshold_heating)
            total_heating_units++;
        if (data[i].humidity > threshold_ventilation)
            total_ventilation_units++;
        int motion_detected = 0;
        for (int j = 0; j < NUM_MOTION_SENSORS; j++) {
            if (data[i].motion_sensors[j] > 0) {
                motion_detected = 1;
                break;
            }
        }
        if (motion_detected)
            total_lights_units++;
    }

    // Calculate energy consumption using total units and hourly consumption rates
    int energy_consumption = (total_heating_units * HEATING_CONSUMPTION) +
                             (total_ventilation_units * VENTILATION_CONSUMPTION) +
                             (total_lights_units * LIGHTS_CONSUMPTION);

    // Write energy consumption to Energy.txt file with timestamp
    FILE *energy_file = fopen("Energy.txt", "a");
    if (energy_file == NULL) {
        printf("Error opening Energy.txt file.\n");
        exit(1);
    }
    
    // Get current timestamp
    time_t rawtime;
    struct tm *info;
    time(&rawtime);
    info = localtime(&rawtime);
    char timestamp[20];
    strftime(timestamp, sizeof(timestamp), "%Y-%m-%d %H:%M:%S", info);
    
    // Write timestamp and energy consumption to file
    fprintf(energy_file, "Timestamp: %s, Energy Consumption: %d KJ\n", timestamp, energy_consumption);
    
    fclose(energy_file);
}

int main() {
    // Declare arrays of structures to hold sensor data for each location
    SensorData living_room[HOURS_PER_DAY];
    SensorData bedroom[HOURS_PER_DAY];
    SensorData kitchen[HOURS_PER_DAY];

    char living_room_file[100], bedroom_file[100], kitchen_file[100];

    // Prompt the user to enter file paths for each location
    printf("Enter file path for living room data: ");
    scanf("%s", living_room_file);
    printf("Enter file path for bedroom data: ");
    scanf("%s", bedroom_file);
    printf("Enter file path for kitchen data: ");
    scanf("%s", kitchen_file);

    // Read data from sensor files for each location
    readDataFile(living_room_file, living_room);
    readDataFile(bedroom_file, bedroom);
    readDataFile(kitchen_file, kitchen);

    // Control devices and calculate energy consumption for each location
    printf("Living Room:\n");
    controlDevices(living_room, 20, 60, 5); // Example threshold values
    calculateEnergyConsumption(living_room, 20, 60, 5);

    printf("\nBedroom:\n");
    controlDevices(bedroom, 18, 50, 3);
    calculateEnergyConsumption(bedroom, 18, 50, 3);

    printf("\nKitchen:\n");
    controlDevices(kitchen, 22, 55, 8);
    calculateEnergyConsumption(kitchen, 22, 55, 8);

    return 0;
}
// 

