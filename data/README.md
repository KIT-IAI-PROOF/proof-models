# JSON Format Conversion

This directory contains JSON files in both old and new formats:

## Old Format Files
- `block_file_line_provider.json`: Contains block information
- `program_file_line_provider.json`: Contains program information
- `attachment_file_line_provider.json`: Contains attachment information

## New Format File
- `test.json`: Example of the new format with nested entities

## Converted File
- `converted_file_line_provider.json`: The old format data converted to the new format

## Conversion Changes
The conversion process involved the following changes:

1. **Nesting Structure**:
   - In the old format, block, program, and attachment were separate entities in different files
   - In the new format, program is nested inside the block, and attachments are nested inside the program

2. **Field Name Changes**:
   - `name` → `label`
   - `programId` → direct `program` object
   - `fileName` → `label` (for attachments)

3. **Type Formatting**:
   - Types like "string" are now uppercase "STRING"

4. **Additional Fields**:
   - Added metadata fields like createdBy, lastModifiedBy, creationDate, lastModifiedDate
   - Added position information
   - Added shutdownRelevant flag

The conversion maintains all the original data while restructuring it to match the new format requirements.