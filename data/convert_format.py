import json
import os
import uuid
from datetime import datetime, timezone

# Define paths
block_file = 'file-provider/block_file_line_provider.json'
program_file = 'file-provider/program_file_line_provider.json'
attachment_file = 'file-provider/attachment_file_line_provider.json'
output_file = 'file-provider/converted_file_line_provider.json'

# Read the input files
with open(block_file, 'r') as f:
    block_data = json.load(f)

with open(program_file, 'r') as f:
    program_data = json.load(f)

with open(attachment_file, 'r') as f:
    attachment_data = json.load(f)

# Create the new format structure
new_format = {
    "id": block_data.get("id", str(uuid.uuid4())),
    "label": block_data.get("name"),  # name -> label
    "name": block_data.get("name"),
    "description": block_data.get("description"),
    "color": "#0D41E1",  # Default color
    "type": "block",
    "containerImage": block_data.get("containerImage"),
    "communicationParadigm": block_data.get("communicationParadigm"),
    "position": {
        "x": 0,
        "y": 20
    },
    "program": {
        "id": program_data.get("id"),
        "label": program_data.get("name"),  # name -> label
        "description": program_data.get("description"),
        "tag": program_data.get("tag"),
        "runtime": program_data.get("runtime"),
        "entryPoint": program_data.get("entryPoint"),
        "attachments": [],
        "createdBy": "anonymous",
        "lastModifiedBy": "anonymous",
        "creationDate": datetime.now(timezone.utc).isoformat(),
        "lastModifiedDate": datetime.now(timezone.utc).isoformat()
    },
    "eventLifeCycles": block_data.get("eventLifeCycles", []),
    "blockIODefinitions": block_data.get("blockIODefinitions", []),
    "outputs": [],
    "inputs": [],
    "createdBy": "anonymous",
    "lastModifiedBy": "anonymous",
    "creationDate": datetime.now(timezone.utc).isoformat(),
    "lastModifiedDate": datetime.now(timezone.utc).isoformat(),
    "shutdownRelevant": False
}

# Add attachments
for attachment_ref in program_data.get("attachments", []):
    if attachment_ref.get("id") == attachment_data.get("id"):
        new_attachment = {
            "id": attachment_data.get("id"),
            "label": attachment_data.get("fileName"),  # fileName -> label
            "description": attachment_data.get("description"),
            "path": f"/proof/latest:{attachment_data.get('fileName')}",
            "createdBy": "anonymous",
            "lastModifiedBy": "anonymous",
            "creationDate": datetime.now(timezone.utc).isoformat(),
            "lastModifiedDate": datetime.now(timezone.utc).isoformat()
        }
        new_format["program"]["attachments"].append(new_attachment)

# Convert inputs
for input_item in block_data.get("inputs", []):
    new_input = {
        "id": str(uuid.uuid4()),
        "required": input_item.get("required"),
        "label": input_item.get("name"),  # name -> label
        "description": f"Input: {input_item.get('name')}",
        "type": input_item.get("type", "OBJECT").upper(),
        "phase": input_item.get("phase"),
        "eventId": input_item.get("eventId"),
        "communicationType": input_item.get("communicationType"),
        "createdBy": "anonymous",
        "lastModifiedBy": "anonymous",
        "creationDate": datetime.now(timezone.utc).isoformat(),
        "lastModifiedDate": datetime.now(timezone.utc).isoformat()
    }
    new_format["inputs"].append(new_input)

# Convert outputs
for output_item in block_data.get("outputs", []):
    new_output = {
        "id": str(uuid.uuid4()),
        "label": output_item.get("name"),  # name -> label
        "description": f"Output: {output_item.get('name')}",
        "type": output_item.get("type", "OBJECT").upper(),
        "phase": output_item.get("phase"),
        "eventId": output_item.get("eventId"),
        "communicationType": output_item.get("communicationType"),
        "createdBy": "anonymous",
        "lastModifiedBy": "anonymous",
        "creationDate": datetime.now(timezone.utc).isoformat(),
        "lastModifiedDate": datetime.now(timezone.utc).isoformat()
    }
    new_format["outputs"].append(new_output)

# Write the output file
with open(output_file, 'w') as f:
    json.dump(new_format, f, indent=2)

print(f"Conversion complete. Output written to {output_file}")