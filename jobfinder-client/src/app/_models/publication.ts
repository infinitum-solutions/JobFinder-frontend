export class Publication {
  uuid: string;
  authorUuid: string;
  title: string;
  description: string;
  content: string;
  visible: boolean;

  constructor(uuid: string,
              authorUuid: string,
              title: string,
              description: string,
              content: string,
              visible: boolean) {
    this.uuid = uuid;
    this.authorUuid = authorUuid;
    this.title = title;
    this.description = description;
    this.content = content;
    this.visible = visible;
  }
}
