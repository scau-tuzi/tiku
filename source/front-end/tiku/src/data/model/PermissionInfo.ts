interface PermissionInfo {
  id?:number,
  name:string,
  url?:string,
  method?:string,
  parentPermission?:number,
  childPermissions?:PermissionInfo[]
}