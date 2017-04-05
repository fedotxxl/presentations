package io.belov.presentations.lombok;

import java.util.Date;

public class PersonInformationUpdateCommand {
    private CommandType commandType;
    private ExternalSourceMeta externalSourceMeta;
    private PersonInformation personInformation;

    public enum CommandType {
        CREATE, UPDATE, DELETE
    }

    public static class ContactInformation {
        private String phone;
        private String email;
        private String otherContacts;

        public ContactInformation() {
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setOtherContacts(String otherContacts) {
            this.otherContacts = otherContacts;
        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }

        public String getOtherContacts() {
            return otherContacts;
        }
    }

    public static class ExternalSourceMeta {
        private String externalSource;
        private String externalId;
        private Date externalLastUpdateTimestamp;
        private String externalLastUpdateEventId;

        public ExternalSourceMeta() {}

        public String getExternalSource() {
            return externalSource;
        }

        public void setExternalSource(String externalSource) {
            this.externalSource = externalSource;
        }

        public String getExternalId() {
            return externalId;
        }

        public void setExternalId(String externalId) {
            this.externalId = externalId;
        }

        public Date getExternalLastUpdateTimestamp() {
            return externalLastUpdateTimestamp;
        }

        public void setExternalLastUpdateTimestamp(Date externalLastUpdateTimestamp) {
            this.externalLastUpdateTimestamp = externalLastUpdateTimestamp;
        }

        public String getExternalLastUpdateEventId() {
            return externalLastUpdateEventId;
        }

        public void setExternalLastUpdateEventId(String externalLastUpdateEventId) {
            this.externalLastUpdateEventId = externalLastUpdateEventId;
        }

    }

    public static class PersonInformation {
        private String id;
        private String surname;
        private String name;
        private String middleName;
        private Date birthDate;
        private String info;
        private String gender;
        private ContactInformation contactInformation;

        public PersonInformation() {
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setMiddleName(String middleName) {
            this.middleName = middleName;
        }

        public void setBirthDate(Date birthDate) {
            this.birthDate = birthDate;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public void setContactInformation(ContactInformation contactInformation) {
            this.contactInformation = contactInformation;
        }

        public String getId() {
            return id;
        }

        public Date getBirthDate() {
            return birthDate;
        }

        public String getDescription() {
            return info;
        }

        public String getGender() {
            return gender;
        }

        public String getSurname() {
            return surname;
        }

        public String getName() {
            return name;
        }

        public String getMiddleName() {
            return middleName;
        }

        public String getInfo() {
            return info;
        }

        public ContactInformation getContactInformation() {
            return contactInformation;
        }
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public ExternalSourceMeta getExternalSourceMeta() {
        return externalSourceMeta;
    }

    public PersonInformation getPersonInformation() {
        return personInformation;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public void setExternalSourceMeta(ExternalSourceMeta externalSourceMeta) {
        this.externalSourceMeta = externalSourceMeta;
    }

    public void setPersonInformation(PersonInformation personInformation) {
        this.personInformation = personInformation;
    }

}
