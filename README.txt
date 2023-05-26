# Project Name: PickerApp

Version: 1.0

## Description:
An application for assigning orders to pickers in stores. 
The goal of the application is to maximize the total value of assigned orders.

## Usage:
Go to the `target` directory where you can find the `PickerApp-1.0.jar` file.
To print out the assigned orders use command: java -jar [path to PickerApp-1.0.jar] [path to store file] [path to orders file]

Example: java -jar user/admin/PickerApp-1.0.jar user/admin/files/store.json user/admin/files/orders.json

The orders are going to print out in a following fashion:
[Picker Id] [Order Id] [Time when the order preparation is to be started]
All of the time values are displayed according to the 24-hour system in hh:mm format.

Example:
P1 order-1 9:00
P1 order-6 9:15
P2 order-5 9:00

## Valid file format:
The application only accepts files of .json extension in format shown below.
The example format of store file:
{
  "pickers": [
    "P1"
  ],
  "pickingStartTime": "09:00",
  "pickingEndTime": "10:00"
}

The example format of orders file:
[
  {
    "orderId": "order-1",
    "orderValue": "1.00",
    "pickingTime": "PT60M",
    "completeBy": "10:00"
  },
  {
    "orderId": "order-2",
    "orderValue": "2.00",
    "pickingTime": "PT15M",
    "completeBy": "09:15"
  }
]

## Author:
Michał Markowski
Contact at: michoch4@gmail.com