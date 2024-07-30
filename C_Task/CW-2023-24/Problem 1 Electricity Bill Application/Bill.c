
#include <stdio.h>
#include <stdlib.h>

// Function to calculate electric bill for urban areas
float calculateUrbanBill(float energyConsumed) {
    float unitPrice;
    if (energyConsumed <= 50)
        unitPrice = 3.0;
    else if (energyConsumed <= 100)
        unitPrice = 3.75;
    else
        unitPrice = 4.50;
    return unitPrice * energyConsumed;
}

// Function to calculate electric bill for rural areas
float calculateRuralBill(float energyConsumed) {
    float unitPrice;
    if (energyConsumed <= 50)
        unitPrice = 2.0;
    else if (energyConsumed <= 100)
        unitPrice = 2.75;
    else
        unitPrice = 4.15;
    return unitPrice * energyConsumed;
}

int main() {
    char name[50], email[50];
    int houseNumber, phoneNumber, meterNumber;
    float energyConsumed;

    // Display welcome message and prompt user for information
    printf("Welcome to the Electricity Bill Application\n");
    printf("Please enter your information:\n");
    printf("Name: ");
    scanf("%s", name);
    printf("House Number: ");
    scanf("%d", &houseNumber);
    printf("Email Address: ");
    scanf("%s", email);
    printf("Phone Number: ");
    scanf("%d", &phoneNumber);
    printf("Meter Number: ");
    scanf("%d", &meterNumber);
    printf("Energy Consumed (in KW): ");
    scanf("%f", &energyConsumed);

    // Display menu for location selection
    printf("\nSelect your location:\n");
    printf("1. Urban\n");
    printf("2. Rural\n");
    int locationChoice;
    scanf("%d", &locationChoice);

    float billAmount;

    // Calculate electric bill based on location choice
    switch (locationChoice) {
        case 1:
            billAmount = calculateUrbanBill(energyConsumed);
            break;
        case 2:
            billAmount = calculateRuralBill(energyConsumed);
            break;
        default:
            printf("Invalid choice. Exiting...\n");
            return 1;
    }

    // Display electric bill with user information
    printf("\nElectric Bill\n");
    printf("Name: %s\n", name);
    printf("House Number: %d\n", houseNumber);
    printf("Email Address: %s\n", email);
    printf("Phone Number: %d\n", phoneNumber);
    printf("Meter Number: %d\n", meterNumber);
    printf("Energy Consumed: %.2f KW\n", energyConsumed);
    printf("Total Bill Amount: £%.2f\n", billAmount);

    // Prompt user to save bill as .txt file
    printf("\nDo you want to save the bill as a .txt file? (1 for Yes, 0 for No): ");
    int saveChoice;
    scanf("%d", &saveChoice);
    if (saveChoice == 1) {
        char fileName[50];
        printf("Enter file name: ");
        scanf("%s", fileName);
        FILE *file = fopen(fileName, "w");
        if (file == NULL) {
            printf("Error opening file. Unable to save bill.\n");
        } else {
            fprintf(file, "Electric Bill\n");
            fprintf(file, "Name: %s\n", name);
            fprintf(file, "House Number: %d\n", houseNumber);
            fprintf(file, "Email Address: %s\n", email);
            fprintf(file, "Phone Number: %d\n", phoneNumber);
            fprintf(file, "Meter Number: %d\n", meterNumber);
            fprintf(file, "Energy Consumed: %.2f KW\n", energyConsumed);
            fprintf(file, "Total Bill Amount: £%.2f\n", billAmount);
            fclose(file);
            printf("Bill saved successfully as %s.txt\n", fileName);
        }
    }

    return 0;
}

